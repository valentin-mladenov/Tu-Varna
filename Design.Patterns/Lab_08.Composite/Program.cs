using System;

namespace Lab_07.Composite
{
    class Program
    {
        static void Main(string[] args)
        {

            var menu = new Menu("Маню", "При Вальо");
            var breakfast = new Menu("Закуска", "Горещи напитки, палачинки и плодова салата");
            var lunch = new Menu("Обяд", "Сандвичи, бургери и супи");
            var dinner = new Menu("Вечеря", "Пици, спагети и бургери");

            menu.Add(breakfast);
            menu.Add(lunch);
            menu.Add(dinner);

            breakfast.Add(new MenuItem("Палачинки", "С мед, и сирене", 2.5, true));
            breakfast.Add(new MenuItem("Плодова салата", "Киви, банан, ябълка и круша", 3, true));
            var hotBevareges = new Menu("Горещи напитки", "Чай и кафе");
            hotBevareges.Add(new MenuItem("Чай", "Черен чай", 1.4, true));
            hotBevareges.Add(new MenuItem("Кафе", "Капучино", 2.0, true));
            breakfast.Add(hotBevareges);

            var burgerT = new MenuItem("Телешки бургер", "Телешко, кашкавал, салата и лук.", 10, false);
            var burgerV = new MenuItem("Вегитариански бургер", "Кашкавал, салата и лук.", 8, false);

            lunch.Add(new MenuItem("Пилешки Сандвич", "Пиле, домат и кашкавал", 7, false));
            lunch.Add(burgerT);
            lunch.Add(burgerV);

            dinner.Add(new MenuItem("Пица Капричоза", "Пеперони, кашкавал и доматен сос.", 210, false));
            dinner.Add(new MenuItem("Спагети Карабоанра", "Мляно месо, спагети доматен сос.", 280, false));
            dinner.Add(burgerT);
            dinner.Add(burgerV);

            var dessert = new Menu("Десерт", "Сладоледи и торти");
            dessert.Add(new MenuItem("Сладолед", "Валилов и шоколадов", 1.5, false));
            dessert.Add(new MenuItem("Торта", "Бисквитена, Шоколадова и СЛаоледена", 180, false));

            dinner.Add(dessert);
            lunch.Add(dessert);

            menu.Print();
        }
    }
}
