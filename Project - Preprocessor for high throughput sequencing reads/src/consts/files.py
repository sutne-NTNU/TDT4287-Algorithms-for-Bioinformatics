import os

cur_path = os.path.dirname(__file__)

RESULTS = os.path.join(cur_path, '../results/')
FIGURES = os.path.join(cur_path, '../../report/results/')

T1_DISTRIB = "task1-length-distribution"
T1_TIMES = "task1-times"

TASK_2 = "task2-"
D = TASK_2 + "length-distribution"
T2_D_10 = D + "-10"
T2_D_25 = D + "-25"
T2_D_10_INDEL = D + "-10-with-indel"
T2_D_25_INDEL = D + "-25-with-indel"
T2_TIMES = TASK_2 + "times"
T2_TIMES_INDEL = TASK_2 + "times-with-indel"

TASK_3 = "task3-"
T3_COMP_T2_10_T1 = TASK_3 + "task2-10-vs-task1"
T3_COMP_T2_25_T1 = TASK_3 + "task2-25-vs-task1"
T3_COMP_T2_25_T2_10 = TASK_3 + "task2-25-vs-task2-10"
T3_COMP_T2_10_INDEL = TASK_3 + "10-indel-vs-10"
T3_COMP_T2_25_INDEL = TASK_3 + "25-indel-vs-25"
T3_COMP_T2_10_INDEL_T1 = TASK_3 + "10-indel-vs-t1"
T3_COMP_T2_25_INDEL_T1 = TASK_3 + "25-indel-vs-t1"

TASK_4 = "task4-"
T4_ORIGINAL_LENGTH = TASK_4 + "original-length"
T4_NEW_LENGTH = TASK_4 + "new-length"
T4_COMP_OLD_NEW = TASK_4 + "length-comparison"
T4_TIMES = TASK_4 + "times"
