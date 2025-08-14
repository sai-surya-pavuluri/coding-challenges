from typing import List, Dict, Optional

def solve_alphametic(addends: List[str], result: str) -> Optional[Dict[str, int]]:
    # 1) Prep
    rev_addends = [w[::-1] for w in addends]
    rev_result = result[::-1]
    max_len = max(map(len, rev_addends + [rev_result]))

    letters = set("".join(addends + [result]))
    leading = {w[0] for w in addends + [result]}

    # fast fail: more letters than digits
    if len(letters) > 10:
        return None

    assignment: Dict[str, int] = {}
    used = [False]*10  # digits 0..9

    def col_chars(col: int):
        cols = [w[col] if col < len(w) else None for w in rev_addends]
        out = rev_result[col] if col < len(rev_result) else None
        return cols, out

    def dfs(col: int, carry: int) -> bool:
        # TODO: base case: if col == max_len -> return carry == 0

        # TODO: gather column chars (inputs, output)
        # inputs: list like [char|None, char|None, ...], out_char|None

        # TODO: compute sum of assigned digits for input chars
        # For unassigned letters, you’ll need to try digits (with constraints).
        # Strategy:
        #   - recursively assign the unassigned input letters for this column
        #   - once inputs set, compute s = sum_inputs + carry
        #   - enforce result digit (assign if needed)
        #   - recurse to next column with new carry

        return False  # change when implemented

    ok = dfs(0, 0)
    return assignment if ok else None
