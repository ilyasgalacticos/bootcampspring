package kz.bootcamp.springboot.springBootcamp.controllers;

import kz.bootcamp.springboot.springBootcamp.beans.DatabaseBean;
import kz.bootcamp.springboot.springBootcamp.beans.FirstBean;
import kz.bootcamp.springboot.springBootcamp.beans.ItemManager;
import kz.bootcamp.springboot.springBootcamp.dto.DBManager;
import kz.bootcamp.springboot.springBootcamp.dto.Items;
import kz.bootcamp.springboot.springBootcamp.entities.Brands;
import kz.bootcamp.springboot.springBootcamp.entities.Categories;
import kz.bootcamp.springboot.springBootcamp.entities.ShopItems;
import kz.bootcamp.springboot.springBootcamp.entities.Users;
import kz.bootcamp.springboot.springBootcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FirstBean firstBean;

    @Autowired
    @Qualifier("kirillBean")
    private ItemManager itemManager;

    @Autowired
    @Qualifier("dbBean")
    private DatabaseBean databaseBean;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/") // @WebServlet(value = "/") + public void doGet(){
    public String index(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        List<ShopItems> shopItems = databaseBean.getAllShopItems();
        model.addAttribute("tovary", shopItems); // request.setAttribute("tovary", items);
        return "index"; // request.getRequestDispatcher("/index.html").forward(request, response)
    }

    @GetMapping(value = "/about")
    public String about(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        firstBean.setName("Ilyas");
        firstBean.setNumber(3456);
        return "about";
    }

    @PostMapping(value = "/adduser") // @WebServlet(value = "/adduser") + public void doPost(){
    public String addUser(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        return "redirect:/"; // response.sendRedirect("/");
    }

    @GetMapping(value = "/additem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        List<Brands> brands = databaseBean.getAllBrands();
        model.addAttribute("brands", brands);
        return "additem";
    }

    @PostMapping(value = "/additem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String toAddItem(@RequestParam(name = "name") String name, //String name = request.getParameter("name")
                            @RequestParam(name = "description") String desc,
                            @RequestParam(name = "price") double price,
                            @RequestParam(name = "amount") int amount,
                            @RequestParam(name = "brand_id") Long brandId){


        Brands brand = databaseBean.getBrand(brandId);

        if(brand!=null){
            ShopItems item = new ShopItems();
            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            item.setDescription(desc);
            item.setBrand(brand);
            databaseBean.addShopItem(item);
            return "redirect:/additem?success"; //response.sendRedirect("/additem?success");
        }

        return "redirect:/additem?error";
    }

    @GetMapping(value = "/details/{itemId}-page.html")
    public String details(@PathVariable(name = "itemId") Long id, Model model){

        model.addAttribute("CURRENT_USER", getUser());

        ShopItems item = databaseBean.getShopItems(id);
        model.addAttribute("tovar", item);

        List<Brands> brands = databaseBean.getAllBrands();
        model.addAttribute("brands", brands);

        List<Categories> categories = databaseBean.getCategories();
        categories.removeAll(item.getCategories());

        model.addAttribute("categories", categories);

        return "details";

    }

    @PostMapping(value = "/saveitem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String toSaveItem(
                            @RequestParam(name = "id") Long id,
                            @RequestParam(name = "name") String name, //String name = request.getParameter("name")
                            @RequestParam(name = "description") String desc,
                            @RequestParam(name = "price") double price,
                            @RequestParam(name = "amount") int amount,
                            @RequestParam(name = "brand_id") Long brandId){

        ShopItems item = databaseBean.getShopItems(id);

        if(item!=null){

            Brands brand = databaseBean.getBrand(brandId);

            if(brand!=null) {

                item.setName(name);
                item.setPrice(price);
                item.setAmount(amount);
                item.setDescription(desc);
                item.setBrand(brand);

                databaseBean.updateItem(item);

                return "redirect:/details/" + id + "-page.html?success";

            }

        }

        return "redirect:/";

    }

    @PostMapping(value = "/deleteitem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteItem(@RequestParam(name = "id") Long id){
        ShopItems item = databaseBean.getShopItems(id);
        if(item!=null){
            databaseBean.deleteItem(id);
        }
        return "redirect:/";
    }

    @PostMapping(value = "/assigncategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String assignCategory(@RequestParam(name = "id") Long id,
                                 @RequestParam(name = "category_id") Long categoryId){

        Categories category = databaseBean.getCategory(categoryId);
        if(category!=null){

            ShopItems item = databaseBean.getShopItems(id);

            if(item!=null){

                List<Categories> categories = item.getCategories();
                if(categories==null){
                    categories = new ArrayList<>();
                }

                if(!categories.contains(category)){
                    categories.add(category);
                }

                item.setCategories(categories);

                databaseBean.updateItem(item);

                return "redirect:/details/"+id+"-page.html";

            }
        }

        return "redirect:/";

    }

    @PostMapping(value = "/removecategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String removeCategory(@RequestParam(name = "id") Long id,
                                 @RequestParam(name = "category_id") Long categoryId){

        Categories category = databaseBean.getCategory(categoryId);
        if(category!=null){

            ShopItems item = databaseBean.getShopItems(id);

            if(item!=null){

                List<Categories> categories = item.getCategories();
                if(categories==null){
                    categories = new ArrayList<>();
                }

                if(categories.contains(category)){
                    categories.remove(category);
                }

                item.setCategories(categories);

                databaseBean.updateItem(item);

                return "redirect:/details/"+id+"-page.html";

            }
        }

        return "redirect:/";

    }

    @GetMapping(value = "/loginpage")
    public String loginPage(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        return "login";
    }

    @GetMapping(value = "/registerpage")
    public String registerPage(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        return "register";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        return "profile";
    }

    @GetMapping(value = "/accessdeniedpage")
    public String accessDeniedPage(Model model){
        model.addAttribute("CURRENT_USER", getUser());
        return "403";
    }

    @PostMapping(value = "/toregister")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_repassword") String rePassword,
                             @RequestParam(name = "user_full_name") String fullName){

        if(password.equals(rePassword)){

            Users user = new Users();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setFullName(fullName);

            if(userService.addUser(user)!=null){

                return "redirect:/registerpage?success";

            }

            return "redirect:/registerpage?emailerror";

        }

        return "redirect:/registerpage?passworderror";

    }

    @PostMapping(value = "/toupdatepassword")
    @PreAuthorize("isAuthenticated()")
    public String updatePassword(@RequestParam(name = "old_password") String oldPassword,
                                 @RequestParam(name = "new_password") String newPassword,
                                 @RequestParam(name = "retype_newpassword") String reNewPassword){

        Users currentUser = getUser();

        if(newPassword.equals(reNewPassword)){

            if(passwordEncoder.matches(oldPassword, currentUser.getPassword())){

                currentUser.setPassword(passwordEncoder.encode(newPassword));
                userService.updateUser(currentUser);
                return "redirect:/profile?passwordsuccess";

            }

            return "redirect:/profile?oldpasserror";

        }

        return "redirect:/profile?passworderror";

    }

    @PostMapping(value = "/toupdateprofile")
    @PreAuthorize("isAuthenticated()")
    public String updateProfile(@RequestParam(name = "full_name") String fullName){

        Users currentUser = getUser();
        currentUser.setFullName(fullName);
        userService.updateUser(currentUser);

        return "redirect:/profile?success";

    }

    private Users getUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users)authentication.getPrincipal();
            return user;
        }else{
            return null;
        }

    }

}