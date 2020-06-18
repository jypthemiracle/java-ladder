package nextstep.ladder.ladder;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.ladder.height.Height;
import nextstep.ladder.line.Lines;
import nextstep.ladder.painter.RandomPaintingStrategyImpl;
import nextstep.ladder.player.Player;
import nextstep.ladder.player.Players;

public class LadderTest {

	@DisplayName("입력한 사다리의 높이만큼 사다리 라인이 그려진다.")
	@Test
	void 입력한_사다리_높이만큼_라인이_그려진다() {
		Players players = Players.ofPlayers(Arrays.asList(Player.ofPlayer("Jin"), Player.ofPlayer("Brian")));
		Height height = Height.ofHeight("5");
		RandomPaintingStrategyImpl randomPaintingStrategy = new RandomPaintingStrategyImpl();
		Ladder ladder = Ladder.ofLadder(players, height, randomPaintingStrategy);
		Lines lines = ladder.getLines();
		int lineSize = lines.size();
		assertThat(height.getHeightValue()).isEqualTo(lineSize);
	}
}