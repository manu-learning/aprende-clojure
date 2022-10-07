MKDIR=mkdir -p
COPY_OPTIONS= --exclude='project.clj' --exclude='HELP.md' \
							--include='*.md' --include='*.clj' \
							--include='*/' --exclude='*' \
							--prune-empty-dirs
COPY=rsync -vzr $(COPY_OPTIONS)

DIR_RELATIVE_ROOT = ./exercism-exercises
DIR_EXERCISM_EXERCISES = clojure

DIRS := $(dir $(wildcard $(DIR_EXERCISM_EXERCISES)/*/))

$(DIR_EXERCISM_EXERCISES):
	@$(MKDIR) $@

copy-exercises: $(DIR_EXERCISM_EXERCISES)
	$(info Copiando ejercicios de la carpeta de Exercism al raíz)
	@$(foreach DIR, $(DIRS), \
		$(COPY) $(DIR) $(subst $(DIR_EXERCISM_EXERCISES),,$(DIR_RELATIVE_ROOT)/$(DIR));\
	)

install:
# Clojure CLI tools and common aliases (Additional tools to enhance Clojure development experience)
	$(info Instalando Clojure + Clojure Tools..)
	@git clone git@github.com:practicalli/clojure-deps-edn.git ~/.clojure/

# Clojure CLI tools (Run Clojure REPL for development and production)
	@mkdir -p /tmp && cd /tmp && curl -O https://download.clojure.org/install/linux-install-1.10.3.967.sh && \
	chmod +x linux-install-1.10.3.967.sh && sudo ./linux-install-1.10.3.967.sh

	$(info Instalando paquetes de linux necesarios (rlwrap leinigen)..)
# A simple read line for command history, only used by clj
	sudo apt install -y rlwrap
#	Leiningen will help you create, build and deploy your Clojure projects.
	sudo apt install -y leiningen

.PHONY: copy-exercises install
