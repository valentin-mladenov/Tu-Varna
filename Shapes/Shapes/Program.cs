using Shapes.Classes;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using static Shapes.Program;

namespace Shapes
{
    class Program
    {
        //class SomeClass
        //{ public static int k = 10;
        //  public SomeClass() { k--; }
        //}


        //        class C { public double a; }
        //        struct A { public double a; }

        //        class Square
        //        {
        //            private int Dimensions;
        //            static Square()
        //            { Dimensions = 4; }
        //            public static int Get_Dimensions
        //            { get { return Dimensions; } }
        //            static void Main()
        //            { Square s = new Square();
        //Console.WriteLine("The square has {0}dimensions", Square.Ge);
        //            } }

        //        }
        //public class Father
        //{ public virtual void Position() { Console.WriteLine("The Father!"); } }
        //public class Child : Father
        //{ public override void Position() { Console.WriteLine("The Child!"); } }

        //static void test(int x, ref int y, out int z) { x += 5; y += 10; z = x + y; }


        //class Calculate
        //{
        //    public double suma(double x, double y) { return x + y; }
        //    public int suma(int x, int y) { return x + y; }
        //    public int suma(int x, int y, int z) { return x + y + z; }
        //    public double suma(double a, double b) { return a - b; }
        //    public int suma(int a, int b) { return a - b; }
        //}

        //interface MyInterface
        //{ int MyProperty { get; set; }
        //    protected void MyMethod();
        //    int this[int i] { get; }
        //}

        //public class GreatFather { }
        //public class Father : GreatFather {  }
        //public class Son : Father, GreatFather { }

        //public class Employee : IComparable
        //{ int id; double salary;

        //    public int CompareTo(object obj){
        //        var inner = (Employee)obj;

        //        return this.salary == inner.salary
        //            ? (this.id == inner.id 
        //                ? (this.id > inner.id ? 1 : -1) 
        //                : 0)
        //            : (this.salary > inner.salary ? -1 : 1);
        //    }
        //}

        //class Picture
        //{
        //    public string Author { get; set; }
        //    public decimal Price { get; set; }
        //    public string Dispaly()
        //    {
        //        return this.ToString();
        //    }

        //    public override string ToString()
        //    {
        //        return $"Author: {this.Author}, Price: {this.Price}";
        //    }
        //}


        //class MyClass { private static int myValue; public int ala() { return myValue; } }
        //class MyStruct : MyClass { public int myValue1; public override void ala() { } }

        //class Family
        //{
        //    private static int Children_number = 2;
        //    public int Children
        //    {
        //        get { return Children_number; }
        //    }
        //}

        //public class man
        //{ private string name; private string EGN; private string SPECIES = "human";
        //    public man(string aName, string aEGN) { name = aName; EGN = aEGN; }
        //    public virtual void display()
        //    { Console.WriteLine(SPECIES); Console.WriteLine(name); Console.WriteLine(EGN); }
        //}

        //public class woman : man
        //{ public woman() : base("Maria", "1234512345"){}
        //    private new string SPECIES = "woman";
        //}

        //static void swap(ref int begin, ref int end, int temp)
        //{ int t; t = begin; begin = end; end = t; temp = begin - end; }

        //interface IPopulation
        //{ long GetPopulatiuon(); }
        //interface IName
        //{ string GetName(); }
        //class Country : IPopulation, IName
        //{
        //    private string name;
        //    private long population;

        //    public Country(string name, long population)
        //    { this.name = name; this.population = population; }

        //    public string GetName() { return this.name; }
        //    public long GetPopulatiuon() { return this.population; }
        //}

        //class Car {
        //    private string model;
        //    private string type;
        //    private int topSpeed;

        //    public Car(string model, int topSpeed)
        //    { this.model = model; this.topSpeed = topSpeed; }

        //    public int TopSpeed { get => this.topSpeed; }
        //    public string Type { set => this.type = value; }

        //    public override string ToString()
        //    { return $"Car with Model: {model}, Type: {type} and Top Speed: {TopSpeed}"; }
        //}

        static void Main()
        {
            //var car = new Car("Mercedess", 220);
            //car.Type = "Sedan";
            //Console.WriteLine(car.ToString());

            //var country = new Country("България", 7000000);
            //Console.WriteLine($"Държава: {country.GetName()}, с население: {country.GetPopulatiuon()}");
            //var balkanCountries = new List<string>();
            //balkanCountries.AddRange(new[] { "България", "Турция", "Гърция", "Македония", "Сърбия", "Албания", "Румъния" });

            //Console.WriteLine(string.Join(", ",balkanCountries.OrderBy(bc => bc)));

            //string s = "Lili";
            //try { int d = Int32.Parse(s); }
            //catch (FormatException)
            //{ Console.WriteLine("Cannot convert the string "); }
            //catch (Exception)
            //{ Console.WriteLine("Can not parse "); }
            //finally { Console.WriteLine(s); }
            //Console.WriteLine(" to Int32!");
            //Hashtable h = new Hashtable();
            //h.Add("key", "Toni");
            //h.Add("key", "Tina");
            //foreach (DictionaryEntry i in h)
            //{   Console.WriteLine(i.Key);
            //    Console.WriteLine("\t{0}", i.Value); } //Край на кода

            //string s = "Hello ";
            //StringBuilder b = new StringBuilder("boys and girls!");
            //string d = s.Remove(4, 1); s.ToUpper();
            //b.Remove(4, 10);
            //Console.WriteLine(d + b);
            //int begin = 10; int end = 100; int temp = 0;
            //swap(ref begin, ref end, temp);
            //Console.WriteLine("begin={0}, end={1}, temp={2}", begin, end, temp);

            //man m = new man("Kalin", "2345123456"); woman w = new woman(); w.display();
            //Console.WriteLine("The family has {0} children", Family.Children);

            //MyClass C1 = new MyClass(); MyClass C2 = new MyClass();
            //MyStruct S1 = new MyStruct(); MyStruct S2 = new MyStruct();
            //C1.myValue = 2; C2 = C1; C2.myValue = 7;
            //S1.myValue = 2; S2 = S1; S2.myValue = 7;
            //Console.WriteLine("{0} {1} {2} {3}", C1.myValue, C2.myValue, S1.myValue, S2.myValue);
            //C1.ala()
            //var pic = new Picture()
            //{
            //    Author = "Majstora",
            //    Price = 123456.99m
            //};

            //Console.WriteLine(pic.Dispaly());

            //var sortedlist = new SortedList();

            //sortedlist.Add("1512114632", "Pencho");
            //sortedlist.Add("7512115361", "Pesho");
            //sortedlist.Add("2510214632", "Ivan");

            //// By Key
            //var searchKeyFor = "1512114632";
            //var foundKey = (sortedlist.Keys as List<string>).Find(x => x.CompareTo(searchKeyFor) == 0);

            //if(foundKey != null) {
            //    var name = sortedlist[searchKeyFor];
            //    sortedlist.Remove(searchKeyFor);
            //}
            //else
            //{ Console.WriteLine("Not found"); }

            //// By Value
            //var searchValueFor = "Ivan";
            //var foundValue = (sortedlist.Values as List<string>).Find(x => x.CompareTo(searchValueFor) == 0);

            //if (foundValue != null) {
            //    var index = sortedlist.IndexOfValue(searchKeyFor);
            //    sortedlist.RemoveAt(index);
            //}
            //else { Console.WriteLine("Not found"); }




            //Hashtable h = new Hashtable();
            //h.Add("table","маса");
            //h.Add("window","прозорец");
            //h.Add("window", "витрина");
            //Son my_son = new Son();
            //Father my_father = new Father();
            //GreatFather my_gr= new GreatFather();
            //string s = "346.56"; try { int.Parse(s); }
            //catch (OverflowException) { Console.WriteLine("Цялото число е твърде голямо цяло число!!!"); }
            //catch (Exception) { Console.WriteLine("s не може да се конвертира!"); }
            //finally { Console.WriteLine("The finaly block"); }

            //var list = new SortedList();
            //list.Add("father", "баща");
            //list.Add("father", "татко");
            //list.Add("father", "майка");

            //SomeClass s = new SomeClass();
            //Console.WriteLine(s.k);

            //string s = "bulgaria is our capital! ";
            //s.Substring(0, 8); s.ToUpper(); s.Trim();
            //Console.WriteLine("1." + s + "*");


            //int x = 5; int y = 10; int z = 0; test(x, ref y, out z);
            //Console.WriteLine("x={0}, y={1}, z={2}", x, y, z);

            //var son = new Child(); var father = new Father(); son.Position(); father = son; father  .Position();

            //a.a = 2;
            //b = a;
            //b.a = 4;
            //Console.WriteLine(a.a + b.a);

            //var sa = new A();
            //var sb = new A();

            //sa.a = 2;
            //sb = sa;
            //sb.a = 4;
            //Console.WriteLine(sa.a + sb.a);

            //var shapes = new List<IShape>();

            //FileIO.ReadShapesFromFile(shapes, "../../files/shapes.csv");

            //FileIO.WriteShapesAsStrings(shapes, "../../files/shapesAsStrings.txt");

            //shapes.Sort();

            //FileIO.WriteShapesAsStrings(shapes, "../../files/shapesSorted.txt");

            //FileIO.WriteShapesAsStrings(
            //    shapes.Where(sh => sh.CalculatePerimeter() == 345.58).ToList(),
            //    "../../files/shapesWIthCommonPerimeter.txt");

            //FileIO.WriteShapesAsStrings(
            //    shapes.OrderBy(sh => sh.CalculatePerimeter()).ToList(),
            //    "../../files/shapesOrderedByPerimeter.txt");

            //FileIO.WriteShapesAsStrings(
            //    shapes.OrderBy(sh => sh.CalculateSurface()).ToList(),
            //    "../../files/shapesOrderedBySurface.txt");

            //FileIO.WriteShapesAsStrings(
            //    shapes.OrderBy(sh => sh.LongestSide()).ToList(),
            //    "../../files/shapesOrderedByLongestSide.txt");

            Console.ReadKey();
        }
    }
}
