# Спомощта на класификаторите: БернулиевБейсов класификатор, Мултиноминален Бейсов класификатор,
# Метод на опорните вектори и Метод на К най-близкия съсед да се изчисли и сравни точността на разпознаване
# на мненията на потребителите въз основа на филмови отзиви, предоставени в корпуса: movie_reviews.

import nltk
from nltk.classify.scikitlearn import SklearnClassifier
from sklearn.svm import LinearSVC
from sklearn.naive_bayes import MultinomialNB, BernoulliNB
from sklearn.neighbors import KNeighborsClassifier
from nltk.corpus import movie_reviews
import random

# създаване на списък от всички документи и тяхната категория: #neg -негативно мнение и pos -позитивно мнeние
docs = [
    (list(movie_reviews.words(fileid)), category)
    for category in movie_reviews.categories()
    for fileid in movie_reviews.fileids(category)
]

# Разместване на случаен принцип всички документи
random.shuffle(docs)

# създаване на списък от всички думи и превръщането им в малки букви
all_words = []
for w in movie_reviews.words():
    all_words.append(w.lower())

# Изчисляване на честотата на срещане на всяка дума
all_words = nltk.FreqDist(all_words)

# Списък с първите 3000 най-често използвани думи в корпуса
word_features = list(all_words.keys())[:3000]


# Създаване на екстрактор, който проверява дали всяка от думите присъства #в даден документ
def find_features(document):
    words = set(document)
    features = {}
    for wf in word_features:
        features[wf] = (w in words)
        return features


feature_sets = [(find_features(rev), category) for (rev, category) in docs]

# обучаващо множество
training_set = feature_sets[:1900]

# тестващо множество
testing_set = feature_sets[1900:]

BNB_classifier = SklearnClassifier(BernoulliNB())
BNB_classifier.train(training_set)
print("BernoulliNB accuracy percent:", (nltk.classify.accuracy(BNB_classifier, testing_set)) * 100)

MNB_classifier = SklearnClassifier(MultinomialNB())
MNB_classifier.train(training_set)
print("MultinomialNB accuracy percent:", (nltk.classify.accuracy(MNB_classifier, testing_set)) * 100)

LinearSVC_classifier = SklearnClassifier(LinearSVC())
LinearSVC_classifier.train(training_set)
print("LinearSVC accuracy percent:", (nltk.classify.accuracy(LinearSVC_classifier, testing_set)) * 100)

KNN_classifier = SklearnClassifier(KNeighborsClassifier())
KNN_classifier.train(training_set)
print("KNN accuracy percent:", (nltk.classify.accuracy(KNN_classifier, testing_set))*100)