package com.p532.brickout.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GamePauseTest.class, GameResetTest.class, LivesCheckerTest.class, PaddleCollisionCheckerTest.class,
		WallCollisionCheckerTest.class })
public class MainSuite {

}
