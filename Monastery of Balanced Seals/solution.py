from typing import List

class Solution:
    def forgeBalancedSeals(self, n: int) -> List[str]:
        vault = []  # stores all valid seals

        def forge(seal, openUsed, closeUsed):
            # If the seal is complete, store it in the vault
            if len(seal) == 2 * n:
                vault.append("".join(seal))
                return

            # Rule 1: Can place an open rune if we still have any left
            if openUsed < n:
                seal.append("(")
                forge(seal, openUsed + 1, closeUsed)
                seal.pop()

            # Rule 2: Can place a close rune only if it's balanced so far
            if closeUsed < openUsed:
                seal.append(")")
                forge(seal, openUsed, closeUsed + 1)
                seal.pop()

        forge([], 0, 0)
        return vault


if __name__ == "__main__":
    sln = Solution()
    print(sln.forgeBalancedSeals(3))
