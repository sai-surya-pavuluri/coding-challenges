from typing import List

def trace_phrase(tablet: List[List[str]], phrase: str) -> bool:
    if not phrase:
        return True
    if not tablet or not tablet[0] or len(phrase) > len(tablet) * len(tablet[0]):
        return False

    m, n = len(tablet), len(tablet[0])

    def dfs(r: int, c: int, i: int) -> bool:
        # matched all chars
        if i == len(phrase):
            return True
        # bounds + visited
        if r < 0 or r >= m or c < 0 or c >= n or tablet[r][c] == '#':
            return False
        ch = tablet[r][c]
        # match or wildcard
        if phrase[i] != '?' and ch != phrase[i]:
            return False

        # mark visited
        tablet[r][c] = '#'
        ok = (dfs(r + 1, c, i + 1) or
              dfs(r - 1, c, i + 1) or
              dfs(r, c + 1, i + 1) or
              dfs(r, c - 1, i + 1))
        # unmark
        tablet[r][c] = ch
        return ok

    # gather starting positions
    starts = [(r, c) for r in range(m) for c in range(n)
              if phrase[0] == '?' or tablet[r][c] == phrase[0]]

    for r, c in starts:
        if dfs(r, c, 0):
            return True
    return False


# ---------- quick tests ----------
if __name__ == "__main__":
    tablet = [
        ['A', 'B', 'C', 'E'],
        ['S', 'F', 'C', 'S'],
        ['A', 'D', 'E', 'E']
    ]

    tests = [
        ("ABCCED", True),
        ("A?CCED", True),
        ("SEE",    True),
        ("ABCB",   False),
        ("?FCE",   True),
    ]

    for ph, expected in tests:
        result = trace_phrase([row[:] for row in tablet], ph)
        print(f"{ph} -> {result} (expected {expected})")
