import sys
from bisect import bisect_right

def read_input():
    data = sys.stdin.read().strip().split()
    n = int(data[0])
    triples = []
    idx = 1
    for _ in range(n):
        s = int(data[idx]); e = int(data[idx+1]); w = int(data[idx+2])
        idx += 3
        triples.append((s, e, w))
    return triples

def max_payout(jobs):
    # Sort by end time
    jobs_sorted = sorted(jobs, key=lambda row: row[1])
    n = len(jobs_sorted)
    if n == 0:
        return 0

    ends = [e for _, e, _ in jobs_sorted]
    dp = [0] * (n + 1)  # dp[i] = best using first i jobs

    for i in range(1, n + 1):
        s, e, w = jobs_sorted[i - 1]
        # j = number of jobs (among first i-1) with end <= s
        j = bisect_right(ends, s, hi=i - 1)
        dp[i] = max(dp[i - 1], dp[j] + w)

    return dp[n]

def main():
    jobs = read_input()
    print(max_payout(jobs))

if __name__ == "__main__":
    main()