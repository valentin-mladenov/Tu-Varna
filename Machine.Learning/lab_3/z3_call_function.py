# Извиквайте функцията function последователно с параметри 1, 2, 3.


def function(x, y=None):
    if y is None:
        y = []
    y.append(x)
    return y


print(function(1))
print(function(2))
print(function(3))
