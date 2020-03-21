# Създайте програма на Python,която да проверява дали въведенадума от потребител е палиндром.

inputString = input()

reversedInput = ''.join(reversed(inputString))

if inputString == reversedInput:
    print("Palindrome")
else:
    print("Not Palindrome")