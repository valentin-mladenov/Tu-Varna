# Създайте програма на Python, съдържаща функция, която да проверява дали дадена дума е палиндром.
# Реализирайте функцията по два начина: чрез оператора print и оператора return.

from lab_2.palindrome_funcs import *

input_string = input()

result = palindrome_return(input_string)
print(result)

palindrome_print(input_string)

