from matplotlib import colorbar
import matplotlib.ticker as ticker
import matplotlib.pyplot as plt
import numpy as np
import os
from math import log10
from consts.files import *


def read_lines(filename: str) -> list:
    """ Read all lines from the given file and return them as a
    list (of strings). Removes tha last line of the file as this
    is always blank """
    with open(f'{RESULTS}{filename}.txt', 'r') as results:
        lines = results.readlines()
    return lines


def plot(filename, x: list, y: list, title: str, xlabel: str, ylabel: str, bar: bool = False, start: int = 0, barcolor='tab:blue'):
    """ Plots the x and y data with a few optional paramteres
    for the different types of plots i need """
    fig, ax = plt.subplots(figsize=(10, 6))
    ax.grid(zorder=0)
    if bar:  # Bar diagram
        color = barcolor
        if barcolor == 'adaptive':
            color = ['tab:green' if value >= 0 else'tab:red' for value in y]
        ax.bar(x, y, zorder=5, color=color, width=1)
    else:  # Line Diagram
        ax.scatter(x, y, zorder=5)

    if start > 0:
        fmt = ticker.FuncFormatter(lambda x, _: scientific(x))
        ax.xaxis.set_major_formatter(fmt)
    ax.set(
        title=title,
        xlabel=xlabel,
        ylabel=ylabel
    )
    fig.savefig(f'{FIGURES}{filename}.png')


def plot_length_distribution(filename, title):
    """ Plots a basic bar diagram with the values from filename"""
    lines = read_lines(filename)
    x = np.arange(0, len(lines), 1)
    y = [float(line) for line in lines]
    plot(
        filename,
        x, y,
        title=title,
        xlabel='Sequence Length (Number of Characters)',
        ylabel='Number Of Occurences',
        bar=True,
    )


def plot_length_distribution_comparison(filename, title):
    """ Plots the values from filename with
    positive=green bars and negative=red bars"""
    lines = read_lines(filename)
    x = np.arange(0, len(lines), 1)
    y = [float(line) for line in lines]
    plot(
        filename,
        x, y,
        title=title,
        xlabel='Sequence Length (Number of Characters)',
        ylabel='Number Of Occurences',
        bar=True,
        barcolor='adaptive',
    )


def plot_execution_times(filename, title, xlabel='Length of Adapter and Sequence', ylabel='Execution Time (ms)'):
    """ Normal line plot that is also automaticcaly formatted based
    on the increment in sequence length (stored in first line in file)"""
    lines = read_lines(filename)
    start, runs, stop = [int(val) for val in lines[0].split(':')]
    x = np.arange(start, stop+start, stop/runs)
    y = [float(line) for line in lines[1:]]
    plot(
        filename,
        x, y,
        title=title,
        xlabel=xlabel,
        ylabel=ylabel,
        start=start
    )


def scientific(x) -> str:
    if x < 10:
        return f'{int(x)}'
    exp = int(log10(x))
    base = int(x / 10**exp)
    return f'{base} x $10^{exp}$'
