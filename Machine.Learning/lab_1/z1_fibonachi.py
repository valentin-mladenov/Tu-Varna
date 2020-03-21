# С помощта на оператора while или for, изчислете и изведете числата на Фибоначи.

fibonacciCount = int(input())

prevFibonacci = 0
currentFibonacci = 1
count = 0

# check if the number of terms is valid
if fibonacciCount <= 0:
    print("Must be  positive integer")
elif fibonacciCount == 1:
    print("Fibonacci sequence:")
    print(prevFibonacci)
else:
    print("Fibonacci sequence:")
    while count < fibonacciCount:
        print(prevFibonacci)
        calculated = prevFibonacci + currentFibonacci

        prevFibonacci = currentFibonacci
        currentFibonacci = calculated
        count += 1
