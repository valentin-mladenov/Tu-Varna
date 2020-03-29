using System;

namespace Lab_09.Facade
{
    class Program
    {
        static void Main(string[] args)
        {
            var dimmer = new Dimmer();
            var dvdPlayer = new DvdPlayer();
            var dvd = new Dvd("Имало едно време в България...");
            var homeTheater = new HomeTheatreFacade(dimmer, dvd, dvdPlayer);

            homeTheater.WatchMovie();
            Console.WriteLine();
            homeTheater.Pause();
            Console.WriteLine();
            homeTheater.Resume();
            Console.WriteLine();
            homeTheater.Pause();
            Console.WriteLine();
            homeTheater.Stop();
        }
    }
}
