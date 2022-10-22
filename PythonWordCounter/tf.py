import re, sys, collections
import os
from concurrent.futures import ThreadPoolExecutor
import threading

# stopwords = set(open('stop_words').read().split(','))
# words = re.findall('\w{3,}', open(sys.argv[1]).read().lower())
# counts = collections.Counter(w for w in words if w not in stopwords)
# for (w, c) in counts.most_common(25):
#     print(w, '-', c)

totalCount = collections.Counter()
lock = threading.Lock()        

def calcWords(file):
    stopwords = set(open('stop_words').read().split(','))
    words = re.findall('\w{3,}', open(file).read().lower())
    counts = collections.Counter(w for w in words if w not in stopwords)
    
    # guard shared mutable state totalCount with a lock
    lock.acquire()
    totalCount.update(counts)
    lock.release()


fileList = []

# create a list of all the txt files to be read
for files in os.listdir("./"):
    if files.endswith(".txt"):
        fileList.append(files)

# start a thread for each txt file
with ThreadPoolExecutor() as exec:
    for file in fileList:
        exec.submit(calcWords, file)

for(w, c) in totalCount.most_common(25):
    print(w, '-', c)