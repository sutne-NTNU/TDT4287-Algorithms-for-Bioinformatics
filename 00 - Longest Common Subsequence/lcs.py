def print_matrix(matrix, S: str, R: str):
    print("\n  - " + " ".join(list(R)))
    for i, row in enumerate(matrix):
        print(('-' if i < 1 else S[i - 1]) + ' ', end='')
        for i, value in enumerate(row):
            print(value if value is not None else '-', end='')
            if i != len(row) - 1:
                print(' ', end='')
        print('')


# Dynamic Programming implementation of LCS problem
def LCS(S, R):
    # find the length of the strings
    m = len(S)
    n = len(R)

    # Initiate lcs matrix
    lcs_matrix = [[None] * (n + 1) for i in range(m + 1)]

    # fill lcs matrix
    for i in range(m + 1):
        for j in range(n + 1):
            # S or R without any letters has no match
            if i == 0 or j == 0:
                lcs_matrix[i][j] = 0

            # If the letters are equal
            elif S[i - 1] == R[j - 1]:
                lcs_matrix[i][j] = lcs_matrix[i - 1][j - 1] + 1
            # if the letters are not equal
            else:
                lcs_matrix[i][j] = max(
                    lcs_matrix[i - 1][j], lcs_matrix[i][j - 1])

    print_matrix(lcs_matrix, S, R)

    # create backtracking matrix
    backtrack_matrix = [[None] * (n + 1) for i in range(m + 1)]
    for i in range(m, 0, -1):
        for j in range(n, 0, -1):
            if lcs_matrix[i][j] == lcs_matrix[i-1][j]:
                backtrack_matrix[i][j] = 0
            elif lcs_matrix[i][j] == lcs_matrix[i][j - 1]:
                backtrack_matrix[i][j] = 1
            elif lcs_matrix[i][j] == lcs_matrix[i-1][j-1] + 1:
                backtrack_matrix[i][j] = 2

    print_matrix(backtrack_matrix, S, R)

    print("the LCS is:")
    # use backtracking matrix to find the sub-sequence

    def printLCS(backtrack_matrix, i, j):
        if i == 0 or j == 0:
            return
        if backtrack_matrix[i][j] == 2:
            printLCS(backtrack_matrix, i - 1, j - 1)
            print(S[i])
        if backtrack_matrix[i][j] == 0:
            printLCS(backtrack_matrix, i - 1, j)
        else:
            printLCS(backtrack_matrix, i, j - 1)

    printLCS(backtrack_matrix, m, n)


if __name__ == "__main__":
    LCS("ATTCGGTTA", "TAGTGATG")
