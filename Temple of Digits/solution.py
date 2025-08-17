class TempleLock:
    def unlockScroll(self, n: int, k: int) -> List[int]:
        valid_codes = []

        def explore_path(path):
            # If path has reached required length, record it
            if len(path) == n:
                valid_codes.append(int("".join(map(str, path))))
                return

            for digit in range(10):
                if not path and digit == 0:   # lock forbids leading zero
                    continue
                if path and abs(digit - path[-1]) != k:  # prune invalid paths
                    continue

                path.append(digit)
                explore_path(path)
                path.pop()

        explore_path([])
        return valid_codes
