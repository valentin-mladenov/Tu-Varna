namespace Lab_09.Facade
{
    public class HomeTheatreFacade
    {
        private Dimmer dimmer;
        private Dvd dvd;
        private DvdPlayer dvdPlayer;
        
        public HomeTheatreFacade(
            Dimmer dimmer, Dvd dvd, DvdPlayer dvdPlayer)
        {
            this.dvd = dvd;
            this.dimmer = dimmer;
            this.dvdPlayer = dvdPlayer;
        }

        public void WatchMovie()
        {
            this.dimmer.Dim(5);
            this.dvdPlayer.On();
            this.dvdPlayer.Insert(this.dvd);
            this.dvdPlayer.Play();
        }

        public void Pause()
        {
            this.dimmer.Dim(10);
            this.dvdPlayer.Pause();
        }

        public void Resume()
        {
            this.dimmer.Dim(5);
            this.dvdPlayer.Resume();
        }
        public void Stop()
        {
            this.dimmer.Dim(10);
            this.dvdPlayer.Stop();
        }
    }
}