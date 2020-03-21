import string


def palindrome_return(user_input: string):
    reversed_input = ''.join(reversed(user_input))

    return user_input == reversed_input


def palindrome_print(user_input: string):
    reversed_input = ''.join(reversed(user_input))

    if user_input == reversed_input:
        print("Palindrome")
    else:
        print("Not Palindrome")
