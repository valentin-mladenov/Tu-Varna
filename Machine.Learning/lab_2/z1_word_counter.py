# Пребройте думите в текст1 с дължина по-голяма от 10.

from nltk.book import text1

wordLen = int(input())

allWords = list(set(text1))

allWordsOverLen = list(filter(lambda word: len(word) > wordLen, allWords))

print(allWordsOverLen)