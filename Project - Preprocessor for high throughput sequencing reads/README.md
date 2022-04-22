# Preprocessor for high throughput sequencing reads

## Project Structure

The project is seperated in three parts, the source code, the report and the presentation. The sourcecode consists of Java classes and executables for analyzing and writing results. Python is used to create plots and images with matplotlib.



```-
TASK 1
Sequences with Suffixes mathing the Adapters Prefix: 542046

TASK 2

Calculating result with just mismathcing
Sequences with Suffixes mathing the Adapters Prefix: 670918
Sequences with Suffixes mathing the Adapters Prefix: 708896

Calculating result when allowing insertions and deletions
Sequences with Suffixes mathing the Adapters Prefix: 676781
Sequences with Suffixes mathing the Adapters Prefix: 713464

TASK 3

Comparing and writing length distributions to file

Comparing Insertions and Deletions

TASK 4

BASELINE: Randomly guessing the adapter (≈25%)
expected:       TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG
actual:         TGCCNTGCCCGGNCAGNAGCCTTGTANNTGTNTATGTNTAGCGATGAGAGCATNNNNNATGTC
matching:       TG---T-C-CGG-----A------T--------A-----A-C----A---C-T--------T- 25,4% Correct

CONTROL: Testing the algorithm for sequences with known adapter
expected:       TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG
actual:         TGGAATTGTTGAATGCCAAGGGCTTCCAGTCACAGAGTGTTCTAATATGC
matching:       TGGAATT-T-G--TGCCAAGG---TCCAGTCACA-AGTG-TCT--TATGC      78,0% Correct

RESULT: Finding most likely adapter in 'tdt4287-unknown-adapter.txt'
Most likely Adapter Sequence is:        TAAAGGATAAGCAGCCGACGTGGGGCGGGTCGAAAAGCGTTAGGATTACTGAGTAGATCGGAAGAGCACACGTCTGAACTCCAGTCACGTAGAGATCTCGTATGCCGTCTTCTGCTTGAA
Removing Perfect Adapter Fragments and writing lengths to file
Comparing Old and New lengths

RESULT: Finding sequence in Seqset3.txt
Sequence found is:      CCCGGGGCGGGAAAGTTTGGGTTGGAAATCTCGGGGGCCAAAAAACCCCCC

TASK 5 - TODO
```