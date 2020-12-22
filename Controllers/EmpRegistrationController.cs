using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using EmployeePortal.Models;



namespace EmployeePortal.Controllers
{
    public class EmpRegistrationController : Controller
    {
        private ApplicationEmpClass _aec;
       

        public EmpRegistrationController(ApplicationEmpClass aec)
        {
            _aec = aec;
           
           
        }
        //Home page 
        public IActionResult Index()
        {
            return View();
        }
        //Registration page
        public IActionResult Create()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]

        //Adding records to the db for registration
        public IActionResult Create(EmpClass ec)
        {
           
            if(ec.EmpName != null && ec.EmpMailId != null && ec.Pwd != null && ec.Pwd == ec.ConfirmPwd)
            {
                _aec.Add(ec);
                _aec.SaveChanges();
                ViewBag.message = "The Employee " + ec.EmpName + "'s details is saved sucessfully..!";
                

            }

            return View();

        }
        
            //Login page
            public IActionResult Create_()
        {
            return View();
        }
        
        //db check for login?? 
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Create_(LogClass lc, EmpClass ec)
        {
            if (lc.EmpMailId.Equals(ec.EmpMailId))
            {
                ViewBag.message = "Welcome"+ec.EmpName;
            }
            else
            {
                ViewBag.message = "User ID not found";
            }
            return View();
        }
        

        
    }
}
