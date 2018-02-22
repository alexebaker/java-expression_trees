all: jar

jar: sources
	@cp README.md src/README.txt && javac @sources.txt && cd src/ && jar cvfe ../spike2.jar Main . && cd ..

sources: FORCE
	@find src/ -name "*.java" > sources.txt

clean: FORCE
	@find src/ -name "*.class" -delete
	@rm src/README.txt sources.txt

TEST_FILES:=$(wildcard tests/*.expi)
TEST_RESULTS:=$(patsubst tests/%.expi, tests/%.expo,$(TEST_FILES))

test: jar $(TEST_RESULTS)

tests/%.expo: tests/%.expi FORCE
	@echo -n "[Test $< -> $@ file: "
	@java -jar spike2.jar $< | diff $@ -
	@echo -n "OK, stdin: "
	@cat $< | java -jar spike2.jar | diff $@ -
	@echo "OK]"

.PHONY: FORCE
