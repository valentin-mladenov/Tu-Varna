# Разгледайте  самостоятелно  методите  на  речника,  като използвате help(dict). Тествайте със собствени примери.

x = dict(USA='English', BG='Bulgarian')

x['RU'] = 'Russian'

print(x)
print(x.keys())
print(x.values())

print(x.pop('RU'))

x.setdefault('DE', 'German')

print(x.get('BG'))

print(x.items())
print(x.popitem())
