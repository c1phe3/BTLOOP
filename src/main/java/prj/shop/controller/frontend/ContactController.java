package prj.shop.controller.frontend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import prj.shop.dto.Contact;

@Controller
public class ContactController {

    @GetMapping("/contact/view")
    public String contactView() {
        return "frontend/contact/contact";
    }

    @PostMapping("/contact/send")
    public String send(final HttpServletRequest request) {

        Contact contact = new Contact(
                request.getParameter("txtName"), // Thay đổi txtName thành txtFirstname
                //request.getParameter("txtLastname"),  // Thêm lastname
                request.getParameter("txtMobile"),
                request.getParameter("txtEmail"),
                request.getParameter("txtAddress"),
               // request.getParameter("txtRequestType"), // Thêm requestType
                request.getParameter("txtMessage"));

        System.out.println("Your firstname: " + contact.getName()); // Thay đổi getName() thành getFirstname()
       // System.out.println("Your lastname: " + contact.getLastname());   // Thêm lastname
        System.out.println("Your email: " + contact.getEmail());
        System.out.println("Your mobile: " + contact.getMobile());
        System.out.println("Your address: " + contact.getAddress());
       // System.out.println("Your request type: " + contact.getRequestType()); // Thêm requestType
        System.out.println("Your message: " + contact.getMessage());

        return "frontend/contact/contact";
    }
}