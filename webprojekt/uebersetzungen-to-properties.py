import shutil

uebersetzungen = {}
with open('uebersetzungen.csv', encoding='utf-8') as f:
    lines = f.readlines()

header = lines[0].strip().split(';')
sprachen = header[1:]

for sprache in sprachen:
    uebersetzungen[sprache] = {}

for line in lines[1:]:
    if line.strip() == '' or line.startswith('#'):
        continue

    teile = line.strip().split(';')
    propertyname = teile[0]

    for i, sprache in enumerate(sprachen):
        uebersetzungen[sprache][propertyname] = teile[i+1]

for sprache in sprachen:
    with open(f'src/main/resources/messages_{sprache}.properties', 'w') as f:
        for key, value in uebersetzungen[sprache].items():
            f.write(f'{key}={value}\n')

shutil.copy(
    f'src/main/resources/messages_{sprachen[0]}.properties',
    'src/main/resources/messages.properties'
)

