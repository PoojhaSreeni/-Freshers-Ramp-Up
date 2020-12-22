using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using Microsoft.AspNetCore.Mvc;

namespace EmployeePortal.Models
{
    public class EmpClass
    {
        [Key]

        public int EmpId { get; set; }
        [Required(ErrorMessage =" Please Enter the Employee Name...")]
        [Display(Name = "Employee Name")]
        public string EmpName { get; set; }
        [Required(ErrorMessage = " Please Enter the Email...")]
        [DataType(DataType.EmailAddress)]
        [Display(Name = "Email")]
        [Remote("IsAlreadySignedUpEmp", "Register", ErrorMessage = "EmailId already exists in database.")]
        public string EmpMailId { get; set; }
        [Required(ErrorMessage = "Enter Date of Birth...")]
        [DataType(DataType.Date)]
        [Display(Name = "DOB")]

        public string EmpDOB { get; set; }
        [Required(ErrorMessage = "Please Enter the password...")]
        [DataType(DataType.Password)]
        [Display(Name = "Password")]

        public string Pwd { get; set; }
        [Required(ErrorMessage = "Please Re-enter the password...")]
        [DataType(DataType.Password)]
        [Display(Name = "Confirm Password")]
        [Compare("Pwd")]

        public string ConfirmPwd { get; set; }
        [Display(Name ="Gender")]

        public string Gender { get; set; }
    }
    
}
