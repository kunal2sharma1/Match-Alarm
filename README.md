# Smart Math Alarm

An Android alarm that requires solving a generated algebra problem before the alarm can be dismissed. Snoozing also requires another problem.

## MVP
- Daily alarm scheduling
- Full-screen alarm activity
- Local algebra problem generation
- Answer validation
- Dismiss only after a correct answer
- Snooze flow
- Local alarm persistence

## Stack
- Kotlin
- Jetpack Compose
- Android AlarmManager
- SharedPreferences for MVP persistence

## Next milestones
1. Verify alarm reliability on a physical Android device.
2. Add configurable snooze duration.
3. Add adaptive difficulty.
4. Add alarm history and statistics.
5. Add ringtone/vibration controls.
