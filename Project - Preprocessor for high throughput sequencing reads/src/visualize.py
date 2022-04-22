from util.util import *
from consts.files import *


def task1():
    plot_length_distribution(
        filename=T1_DISTRIB,
        title=f'Length Distribution After Removing Perfect Suffix Matches'
    )
    plot_execution_times(
        filename=T1_TIMES,
        title='Execution Times For Perfect Prefix-Suffix Matching'
    )


def task2():
    # Only mismatching
    plot_length_distribution(
        filename=T2_D_10,
        title=f'Length Distribution with {10}% Error Acceptance'
    )
    plot_length_distribution(
        filename=T2_D_25,
        title=f'Length Distribution with {25}% Error Acceptance'
    )
    # With insertions and deletions
    plot_length_distribution(
        filename=T2_D_10_INDEL,
        title=f'Length Distribution with {10}% Error Acceptance When Allowing Insertions and Deletions'
    )
    plot_length_distribution(
        filename=T2_D_25_INDEL,
        title=f'Length Distribution with {25}% Error Acceptance When Allowing Insertions and Deletions'
    )
    # Execution times of the two alternatives
    plot_execution_times(
        filename=T2_TIMES,
        title='Execution Times For Imperfect Prefix-Suffix Matching (10% mismatch)'
    )
    plot_execution_times(
        filename=T2_TIMES_INDEL,
        title='Execution Times For Imperfect Prefix-Suffix Matching (10% mismatch)'
    )


def task3():

    plot_length_distribution_comparison(
        filename=T3_COMP_T2_10_T1,
        title=f'Difference In Distribution With {10}% Error Acceptance VS {0}% Error Acceptance'
    )
    plot_length_distribution_comparison(
        filename=T3_COMP_T2_25_T1,
        title=f'Difference In Distribution With {25}% Error Acceptance VS {0}% Error Acceptance'
    )
    plot_length_distribution_comparison(
        filename=T3_COMP_T2_10_INDEL_T1,
        title=f'Difference In Distribution With {10}% Error Acceptance When Allowing Insertions and Deletions VS {0}% Error Acceptance'
    )
    plot_length_distribution_comparison(
        filename=T3_COMP_T2_25_INDEL_T1,
        title=f'Difference In Distribution With {25}% Error Acceptance When Allowing Insertions and Deletions VS {0}% Error Acceptance'
    )
    plot_length_distribution_comparison(
        filename=T3_COMP_T2_10_INDEL,
        title=f'Difference In Distribution With {10}% Error Acceptance When Allowing Insertions and Deletions'
    )
    plot_length_distribution_comparison(
        filename=T3_COMP_T2_25_INDEL,
        title=f'Difference In Distribution With {25}% Error Acceptance When Allowing Insertions and Deletions'
    )


def task4():
    plot_length_distribution_comparison(
        filename=T4_COMP_OLD_NEW,
        title=f'Length Distribution VS Original After Removing Most Likely Adapter Fragments'
    )
    plot_execution_times(
        filename=T4_TIMES,
        title='Execution Times For Finding Most Likely Adapter From A Set of Sequences',
        xlabel='Number Of Sequences In The Set',
    )


def task5():
    pass


if __name__ == "__main__":

    print('Plotting Task 1')
    task1()

    print('Plotting Task 2')
    task2()

    print('Plotting Task 3')
    task3()

    print('Plotting Task 4')
    task4()

    print('Plotting Task 5')
    task5()

    print('DONE')
