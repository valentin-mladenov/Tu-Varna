using System;

namespace Lab_14.Command
{
    class Program
    {
        private static void Main()
        {
            var remote = new RemoteControl(3);

            var bike = new Garage("Мотор");
            var bikeDoorClose = new GarageDoorCloseCommand(bike);
            var bikeDoorOpen = new GarageDoorOpenCommand(bike);


            var garageButton = new OnOffStruct
            {
                On = bikeDoorOpen,
                Off = bikeDoorClose
            };

            remote[0] = garageButton;
            remote.PushOn(0);
            remote.PushUndo();
            remote.PushOff(0);
            remote.PushUndo();

            Console.WriteLine();
            var car = new Garage("Кола");
            var carDoorClose = new GarageDoorCloseCommand(car);
            var carDoorOpen = new GarageDoorOpenCommand(car);

            var light = new Light("Зала");
            ICommand[] partyOn = { new LightOffCommand(light), bikeDoorOpen, carDoorOpen };
            ICommand[] partyOff = { new LightOnCommand(light), bikeDoorClose, carDoorClose };


            remote[2] = new OnOffStruct { On = new MacroCommand(partyOn), Off = new MacroCommand(partyOff) };

            try
            {
                remote.PushOn(2);
                Console.WriteLine();
                remote.PushOff(2);
            }
            catch (Exception)
            {
                Console.WriteLine("Опаааа");
            }
        }
    }
}
