# Създайте функцияна Python, която да изчислява най-големия общ делител на две числа, зададени като параметри на функцията.

from math import gcd


def compute_gcd(first: int, second: int):
    lower = second if first > second else first

    for i in range(1, lower + 1):
        if (first % i == 0) and (second % i == 0):
            out = i

    return out


first_num = int(input())
second_num = int(input())

# Вътрешна за Python
result = gcd(first_num, second_num)

print(result)

# Написана
result_other = compute_gcd(first_num, second_num)

print(result_other)
