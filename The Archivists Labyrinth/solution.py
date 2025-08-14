# The Archivist’s Labyrinth — enumerate every unique rune order to unlock the vault.

from typing import List

def unlock_vault_sequences(runes: List[int]) -> List[List[int]]:
    """
    Given a list of runes (integers, possibly with duplicates), return all unique
    sequences (permutations) that can unlock the Archivist's vault.

    Strategy:
      - Sort runes so identical runes are adjacent.
      - Use a 'warded' array to mark indices already etched into the current sequence.
      - At each depth, never pick a rune that is the same as its left twin unless the twin is already used.
    """
    relics: List[List[int]] = []
    runes.sort()
    n = len(runes)
    warded = [False] * n  # which rune indices are already etched into the path

    def traverse(path: List[int]) -> None:
        if len(path) == n:
            relics.append(path[:])
            return

        for i in range(n):
            if warded[i]:
                continue
            # Same-depth dedupe: don’t choose this twin before its left twin
            if i > 0 and runes[i] == runes[i - 1] and not warded[i - 1]:
                continue

            path.append(runes[i])
            warded[i] = True
            traverse(path)
            warded[i] = False
            path.pop()

    traverse([])
    return relics


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        return unlock_vault_sequences(nums)
    
if __name__ == "__main__":
    sln = Solution()
    print(sln.permuteUnique([1,2,1,3]))
