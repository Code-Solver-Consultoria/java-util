FROM debian:trixie

RUN apt update && \
	apt install -y maven ant sudo git git-flow openjdk-25-jdk \
	zsh curl nano
	
RUN useradd -c 'VSCode Desenv User' -m -p vscode -s /bin/zsh vscode

ARG USERNAME=vscode
RUN echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME
	
# Set `DEVCONTAINER` environment variable to help with orientation
ENV DEVCONTAINER=true

USER vscode

RUN sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
RUN git clone --depth=1 https://github.com/romkatv/powerlevel10k.git ${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k

COPY .devcontainer/.zshrc /home/vscode/.zshrc