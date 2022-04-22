---
title: Preprocessor for high throughput sequencing reads
author: Sivert Utne
date: \today

lang: en-US
papersize: a4
geometry: margin=4cm
toc: true
toc-title: 'Contents'
toc-depth: 2
numbersections: true
colorlinks: true
links-as-notes: true
link-citations: true
bibliography: references.bib
csl: apa.csl
figPrefix: "Figure"
tblPrefix: "Table"
eqnPrefix: "Equation"
---

\clearpage

# Introduction
My introduction

# Methods
My methods

# Results and Discussion
All figures, such as [@fig:figure1], must be referenced in the text.

![Figure legends are placed below figures. Figures should have axis titles.](figures/task1-length-distribution.png){#fig:figure1 width=\textwidth}

It floats and may as a result move to a different page depending on the layout.

Similarly, all tables, such as [@tbl:table1], must also be referenced in the text.

| Barcode | Number of sequences |
| :---    | :---:               |
| AAA     | 1                   |
| CCC     | 2                   |
| GGG     | 3                   |
| TTT     | 4                   |
: Table legends are placed above tables. All columns should be explained in the legend, as illustrated in the following:“Barcode”are identified barcode sequences; “Number of sequences”are the number of sequences having the corresponding barcode.
{#tbl:table1}

\clearpage

What about table [@tbl:table2]?

| Barcode | Number of sequences |
| :---    | :---:               |
| AAA     | 45345               |
| CCC     | 2435345             |
: Just another table.
{#tbl:table2}

In a paragraph you can do stuff like add a link to NTNU[@ntnu-label], or UIO[@uio-label]. If you want you can also put stuff in a footnote[^label].

[^label]: In footnotes you can write anything tangentially related.

This is on the second page

This is a paragraph with inline \LaTeX\ math: $\frac{1}{2}$.
Below is a math block we can reference with [@eq:eq1]:

$$ \int_{a}^{b} f(x)dx $$ {#eq:eq1}

\clearpage
# References{-}