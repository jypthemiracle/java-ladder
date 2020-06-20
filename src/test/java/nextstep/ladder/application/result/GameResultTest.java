package nextstep.ladder.application.result;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nextstep.ladder.application.prize.Prize;
import nextstep.ladder.application.prize.Prizes;
import nextstep.ladder.domain.line.Line;
import nextstep.ladder.domain.line.LineMock;
import nextstep.ladder.domain.line.Lines;
import nextstep.ladder.domain.player.Player;
import nextstep.ladder.domain.player.Players;
import nextstep.ladder.domain.point.Point;
import nextstep.ladder.domain.point.Points;

public class GameResultTest {

	public static Stream<Arguments> 사용자와_상금의_인덱스값_모음() {
		Points pointsOfFirstLine = Points.ofPoints(Arrays.asList(
			Point.ofPoint(false),
			Point.ofPoint(true),
			Point.ofPoint(false),
			Point.ofPoint(false)
		));

		Points pointsOfSecondLine = Points.ofPoints(Arrays.asList(
			Point.ofPoint(true),
			Point.ofPoint(false),
			Point.ofPoint(false),
			Point.ofPoint(false)
		));

		Points pointsOfThirdLine = Points.ofPoints(Arrays.asList(
			Point.ofPoint(false),
			Point.ofPoint(false),
			Point.ofPoint(true),
			Point.ofPoint(false)
		));

		LineMock firstLine = LineMock.ofPoints(pointsOfFirstLine);
		LineMock secondLine = LineMock.ofPoints(pointsOfSecondLine);
		LineMock thirdLine = LineMock.ofPoints(pointsOfThirdLine);

		List<Line> line = Arrays.asList(firstLine, secondLine, thirdLine);
		Lines lines = Lines.ofLines(line);

		Players players = Players.ofPlayers(Arrays.asList(
			Player.ofNameAndPosition("Brian", 0),
			Player.ofNameAndPosition("Alex", 1),
			Player.ofNameAndPosition("Ever", 2),
			Player.ofNameAndPosition("Jin", 3))
		);

		Prizes prizes = Prizes.ofPrizes(Arrays.asList(
			Prize.ofName("꽝"),
			Prize.ofName("3000"),
			Prize.ofName("5000"),
			Prize.ofName("꽝")
		));

		return Stream.of(
			Arguments.of(lines, players, prizes)
		);
	}

	@DisplayName("사용자와 상금의 인덱스 값을 비교하여 맵을 생성하여 게임결과 객체를 반환한다.")
	@MethodSource("사용자와_상금의_인덱스값_모음")
	@ParameterizedTest
	void 사용자와_상금의_인덱스를_비교하여_반환한다(Lines lines, Players players, Prizes prizes) {
		//given
		Players newPlayers = lines.determinePlayersPositionResult(players);
		//when
		GameResult gameResult = GameResult.ofPlayersAndPrizes(newPlayers, prizes);
		//then
		assertAll(
			() -> assertThat(gameResult.getPlayerPrizes()).isNotEmpty(),
			() -> assertThat(gameResult.getPlayerPrizes().get(players.getPlayers().get(0)).getName()).isEqualTo("3000"),
			() -> assertThat(gameResult.getPlayerPrizes().get(players.getPlayers().get(1)).getName()).isEqualTo("꽝"),
			() -> assertThat(gameResult.getPlayerPrizes().get(players.getPlayers().get(2)).getName()).isEqualTo("꽝"),
			() -> assertThat(gameResult.getPlayerPrizes().get(players.getPlayers().get(3)).getName()).isEqualTo("5000")
		);
	}
}