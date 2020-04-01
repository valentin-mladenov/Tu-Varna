# В английския език имената на мъжете и жените имат отличителни черти.
# Имената на жените завършват на гласните: a, e i, докато имената завършващи н аk,o,r,s и t, вероятно са мъжки.
# Да се създаде скрип, който според името на човек с английско име, извежда информация, от какъв пол е.
# Да се използва корпуса names и вградения класификатор nltk.NaiveBayesClassifier
# и да се изчисли колко процента е точността на върнатата информация.


import nltk
from nltk.classify.naivebayes import NaiveBayesClassifier
from nltk.corpus import names
import random


def train_feature(name):
    return {'last_letter': name[-1]}


labeled_names = (
    [(name, 'male') for name in names.words('male.txt')] +
    [(name, 'female') for name in names.words('female.txt')]
)

random.shuffle(labeled_names)
print(labeled_names)

feature_sets = [
    (train_feature(name), gender)
    for (name, gender) in labeled_names
]

# обучаващо множество
training_set = feature_sets[:500]

# тестващо множество
testing_set = feature_sets[500:]

NBC_classifier = NaiveBayesClassifier.train(training_set)
print("NBC accuracy percent:", (nltk.classify.accuracy(NBC_classifier, testing_set))*100)

# Успеваемоста варира от 72 до 76 %.
# NBC accuracy percent: 75.80601826974744
# NBC accuracy percent: 75.94035464803869
# NBC accuracy percent: 74.05964535196131
# NBC accuracy percent: 73.87157442235358
# NBC accuracy percent: 72.1117678667383
