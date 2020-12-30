using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace MSTestProject
{
    class ReaderClass
    {
        public string ReadText(String fileName)
        {
            string builder = "";
            try
            {
                using (StreamReader reader = File.OpenText(fileName))
                {
                    string line;
                    while((line = reader.ReadLine()) != null)
                    {
                        builder += line;
                    }
                    return builder;
                }
            }
            catch(Exception e)
            {
                Console.WriteLine("The file cannot be read!");
                Console.WriteLine(e.Message);
                return null;
            }
        }
    }
}
