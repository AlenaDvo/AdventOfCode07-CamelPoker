import org.junit.jupiter.api.Test;
import part1.CamelPoker;

import static org.assertj.core.api.Assertions.assertThat;

class CamelPokerTest {

    @Test
    void calculateWinnings00() throws Exception {
        CamelPoker camelPoker = new CamelPoker();
        assertThat(camelPoker.calculateWinnings("src/test/resources/aoc07camelpoker00.txt")).isEqualTo(6440);
    }

    @Test
    void calculateWinnings01() throws Exception {
        CamelPoker camelPoker = new CamelPoker();
        assertThat(camelPoker.calculateWinnings("src/test/resources/aoc07camelpoker01.txt")).isEqualTo(251106089);
    }
}