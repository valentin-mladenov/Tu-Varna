# Намерете  честотата  на  срещане  на  думите  в  жанра 'news',  на текстовете в корпуса brown
from collections import Counter

from nltk.corpus import brown

wordsInNews = brown.words(categories='news')

result = Counter(i.lower() for i in wordsInNews)

print(result)
