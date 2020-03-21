# Създайте програма на Python, която да преброява гласните във въведена дума от потребител.
# Използвайте функциите lower() и count().

inputString = input()

inputString = inputString.lower()
vowelCount = 0

for char in inputString.lower():
    if char in 'aeiouаеоуиюя':
        vowelCount += 1

print('Vowles in word: ', vowelCount)
