using System;

namespace Lab_17.Mediator
{
    class Program
    {
        static void Main(string[] args)
        {
            // Create chatroom
            Chatroom chatroom = new Chatroom();

            // Create participants and register them
            Participant joro = new Male("Георги");
            chatroom.Register(joro);

            Participant pesho = new Male("Пешо");
            chatroom.Register(pesho);

            Participant mincho = new Male("Минчо");
            chatroom.Register(mincho);

            Participant lila = new Female("Лила");
            chatroom.Register(lila);

            Participant dancheto = new Female("Данчето");
            chatroom.Register(dancheto);

            // Chatting participants

            dancheto.Send("Георги", "Опа сладур!");
            pesho.Send("Минчо", "Брат хайде да пием бира");
            mincho.Send("Лила", "Тая вечер съм с приятели");
            lila.Send("Пешо", "Остай Минча на мира");
            joro.Send("Данчето", "Как сииии?");
        }
    }
}
