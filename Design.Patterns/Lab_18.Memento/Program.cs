using System;

namespace Lab_18.Memento
{
    class MainApp

    {

        static void Main()
        {
            var saleProspect = new SalesProspect();
            saleProspect.Name = "Чичо Пешо от втория етаж";
            saleProspect.Phone = "0872 560 990";
            saleProspect.Budget = 25000.0;

            Console.WriteLine("Създаване на клиент: " + saleProspect.ToString());
            // Store internal state

            Console.WriteLine("\n-- Запазване на състоянието --");
            Console.WriteLine("Запазен на клиент: " + saleProspect.ToString());
            ProspectMemory saleMemento = new ProspectMemory();
            saleMemento.Memento = saleProspect.SaveMemento();

            // Continue changing originator
            saleProspect.Name = "Иван Петров";
            saleProspect.Phone = "0877 201 990";
            saleProspect.Budget = 1000000.0;
            Console.WriteLine("\nНов клиент: " + saleProspect.ToString());

            // Restore saved state
            saleProspect.RestoreMemento(saleMemento.Memento);
            Console.WriteLine("\n-- Възтрановяване не състоянието --");
            Console.WriteLine("Запазеният клиент: " + saleProspect.ToString());
        }
    }
}
