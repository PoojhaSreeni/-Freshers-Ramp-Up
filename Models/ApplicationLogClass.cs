using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace EmployeePortal.Models
{
    public class ApplicationLogClass : DbContext
    {
        public ApplicationLogClass(DbContextOptions<ApplicationLogClass> options) : base(options)
        {
                
        }
        public DbSet<LogClass> EmpReg { get; set; }
    }
}



