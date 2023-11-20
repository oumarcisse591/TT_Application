package com.slabtech.ttapplication.Web;

import com.slabtech.ttapplication.Dao.BeneficiaryDao;
import com.slabtech.ttapplication.Dao.ClientDao;
import com.slabtech.ttapplication.Dao.ReceiptDao;
import com.slabtech.ttapplication.Dao.SocietyDao;
import com.slabtech.ttapplication.Entity.Beneficiary;
import com.slabtech.ttapplication.Entity.Client;
import com.slabtech.ttapplication.Entity.Receipt;
import com.slabtech.ttapplication.Entity.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class ReceiptController {
    private ReceiptDao receiptDao;
    private ClientDao clientDao;
    private SocietyDao societyDao;

    private BeneficiaryDao beneficiaryDao;


    @Autowired
    public ReceiptController(ReceiptDao theReceiptDao, ClientDao theClientDao, SocietyDao theSocietyDao, BeneficiaryDao theBeneficiaryDao){

        receiptDao = theReceiptDao;
        clientDao = theClientDao;
        societyDao = theSocietyDao;
        beneficiaryDao = theBeneficiaryDao;
    }

    @GetMapping("/list-receipt")
    public String getReceiptList(Model theModel){
        List<Receipt> theReceipts = receiptDao.findAllReceipt();
        Receipt theReceipt = new Receipt();
        theModel.addAttribute("receipts", theReceipts);
        theModel.addAttribute("receipt", theReceipt);
        return "receipt/list-receipt";
    }

    @GetMapping("get-receipt")
    public String getReceipt(@RequestParam("id") int theId, Model theModel){
        Receipt theReceipt = receiptDao.findReceiptById(theId);
        double amount = theReceipt.getAmount();

        theModel.addAttribute("receipt", theReceipt);
        return "receipt/recepisse";
    }
    @GetMapping("update-receipt")
    public String updateReceipt(@RequestParam("id") int theId, Model theModel){
        Receipt theReceipt = receiptDao.findReceiptById(theId);
        List<Client> theClients = clientDao.findAllClient();
        List<Society> theSocieties = societyDao.findAllSociety();
        List<Beneficiary> theBeneficiaries = beneficiaryDao.findAllBeneficiary();
        theModel.addAttribute("beneficiaries", theBeneficiaries);
        theModel.addAttribute("receipt", theReceipt);
        theModel.addAttribute("clients", theClients);
        theModel.addAttribute("societies", theSocieties);
        return "receipt/update-receipt";
    }

    @GetMapping("/add-receipt")
    public String addReceipt(Model theModel){
        Receipt theReceipt = new Receipt();
        Society theSociety = new Society();
        List<Client> theClients = clientDao.findAllClient();
        List<Society> theSocieties = societyDao.findAllSociety();
        List<Beneficiary> theBeneficiaries = beneficiaryDao.findAllBeneficiary();
        theModel.addAttribute("beneficiaries", theBeneficiaries);
        theModel.addAttribute("society", theSociety);
        theModel.addAttribute("societies", theSocieties);
        theModel.addAttribute("clients", theClients);
        theModel.addAttribute("receipt", theReceipt);
        return "receipt/ajout-receipt";
    }

    @PostMapping("/save-receipt")
    public String saveReceipt(@ModelAttribute("receipt") Receipt theReceipt, RedirectAttributes redirectAttributes){
        LocalDate today = LocalDate.now();
        Date now = new Date();
        String date = today.format(DateTimeFormatter.ofPattern("yy"));
        String month = today.format(DateTimeFormatter.ofPattern("MM"));

//        Receipt lastReceipt = receiptDao.findFirstByOrderByUdDesc();
//        int nextId = lastReceipt.getId() + 1;

        // Create a random object
        Random random = new Random();

        // Generate a random number between 100000 and 999999
        int randomNumber = random.nextInt(900000) + 100000;

        theReceipt.setReceiptCode("LMX" + month + date + "-" + randomNumber);
        theReceipt.setReceiptDate(now);
        receiptDao.saveReceipt(theReceipt);
        redirectAttributes.addFlashAttribute("message", "La transaction a été enregistrée avec succès");
        return "redirect:/list-receipt";
    }

    @PostMapping("/saveupdate-receipt")
    public String saveUpdateReceipt(@ModelAttribute("receipt") Receipt theReceipt, RedirectAttributes redirectAttributes){
        receiptDao.saveReceipt(theReceipt);
        redirectAttributes.addFlashAttribute("message", "Le recu a été modifié");
        return "redirect:/list-receipt";
    }

    @GetMapping("delete-receipt")
    public String deleteReceipt(@RequestParam("id") int theId, RedirectAttributes redirectAttributes){
        clientDao.deleteClientById(theId);
        redirectAttributes.addFlashAttribute("message", "Le ticket a été supprimé");
        return "receipt/list-receipt";
    }
}
