import re, sys, collections
import os

# stopwords = set(open('stop_words').read().split(','))
# words = re.findall('\w{3,}', open(sys.argv[1]).read().lower())
# counts = collections.Counter(w for w in words if w not in stopwords)
# for (w, c) in counts.most_common(25):
#     print(w, '-', c)

totalCount = collections.Counter()

for files in os.listdir("./"):
    if files.endswith(".txt"):
        stopwords = set(open('stop_words').read().split(','))
        words = re.findall('\w{3,}', open(files).read().lower())
        counts = collections.Counter(w for w in words if w not in stopwords)
        totalCount.update(counts)
for(w, c) in totalCount.most_common(25):
    print(w, '-', c)
        
        