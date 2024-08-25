spotless {
    kotlin {
        target '**/*.kt'
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}