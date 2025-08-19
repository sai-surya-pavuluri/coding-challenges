class Solution:
    def cleanRoom(self, robot):
        """
        :type robot: Robot
        :rtype: None
        """

        # Directions are relative to our virtual grid:
        # 0 = up, 1 = right, 2 = down, 3 = left
        directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        visited = set()

        def go_back():
            """Move back to the previous cell and restore orientation."""
            robot.turnRight()
            robot.turnRight()
            robot.move()
            robot.turnRight()
            robot.turnRight()

        def dfs(r, c, d):
            """
            DFS from virtual cell (r, c) while the robot is *physically* there
            facing direction index d.
            """
            visited.add((r, c))
            robot.clean()

            # Try 4 directions in order, rotating right each time.
            for i in range(4):
                nd = (d + i) % 4
                dr, dc = directions[nd]
                nr, nc = r + dr, c + dc

                # If not visited, try to move forward (if open).
                if (nr, nc) not in visited:
                    # Face the direction nd (we rotate step-by-step as we iterate).
                    # Since at the start of the loop we are facing d,
                    # and we call turnRight() once per iteration,
                    # the robot's orientation will match nd naturally.
                    if robot.move():
                        dfs(nr, nc, nd)
                        # Backtrack to (r, c) and restore orientation
                        go_back()

                # Rotate right to align with the next direction for the next iteration.
                robot.turnRight()

            # After 4 rotations, the robot's orientation returns to the original d.

        # Start DFS at (0,0) facing up (0)
        dfs(0, 0, 0)
