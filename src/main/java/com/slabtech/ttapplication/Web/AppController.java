package com.slabtech.ttapplication.Web;

import com.slabtech.ttapplication.Dao.ClientDao;
import com.slabtech.ttapplication.Dao.ReceiptDao;
import com.slabtech.ttapplication.Dao.UserDao;
import com.slabtech.ttapplication.Entity.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {
    ReceiptDao receiptDao;
    ClientDao clientDao;
    UserDao userDao;

    @Autowired
    public AppController(ReceiptDao theReceiptDao, ClientDao theClientDao, UserDao theUserDao){
        clientDao = theClientDao;
        receiptDao = theReceiptDao;
        userDao = theUserDao;
    }
    @GetMapping("/")
    public String showHome(Model theModel) {
        List<Receipt> theReceipts = receiptDao.findAllReceipt();
        Receipt theReceipt = new Receipt();
        Long receiptCount = receiptDao.getCount();
        Long clientCount = clientDao.getClientCount();
        Long userCount = userDao.getUserCount();
        theModel.addAttribute("receiptCount", receiptCount);
        theModel.addAttribute("clientCount", clientCount);
        theModel.addAttribute("userCount", userCount);
        theModel.addAttribute("receipts", theReceipts);
        theModel.addAttribute("receipt", theReceipt);
        return "index";
    }

   @GetMapping("/faq")
    public String getFaqPage(){
        return "faq/faq-page";
   }
}
