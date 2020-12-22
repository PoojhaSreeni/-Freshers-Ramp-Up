using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace EmployeePortal.Models
{
    public class ApplicationEmpClass : DbContext
    {
        public ApplicationEmpClass(DbContextOptions<ApplicationEmpClass> options) : base(options)
        {

        }
        public DbSet<EmpClass> EmpReg { get; set; }

    }

}
