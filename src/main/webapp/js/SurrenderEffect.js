let hoverBox = null;
let boxBounds = null;
let glowAlpha = 0;

function setup() {
  let canvas = createCanvas(windowWidth, windowHeight);
  canvas.position(0, 0);
  canvas.style('pointer-events', 'none'); // allow clicks through
  noFill();
}

function draw() {
  clear();
  if (hoverBox && boxBounds) {
    glowAlpha = map(sin(frameCount * 0.1), -1, 1, 50, 150);
    stroke(255, 255, 255, glowAlpha);
    strokeWeight(4);
    rect(boxBounds.x - 5, boxBounds.y - 5, boxBounds.width + 10, boxBounds.height + 10, 16);
  }
}

function getBounds(element) {
  const rect = element.getBoundingClientRect();
  return {
    x: rect.left + window.scrollX,
    y: rect.top + window.scrollY,
    width: rect.width,
    height: rect.height
  };
}

function mouseMoved() {
  const blocks = document.querySelectorAll('.clickable-surrender');
  hoverBox = null;
  boxBounds = null;

  blocks.forEach((block) => {
    const rect = block.getBoundingClientRect();
    if (
      mouseX >= rect.left &&
      mouseX <= rect.right &&
      mouseY >= rect.top &&
      mouseY <= rect.bottom
    ) {
      hoverBox = block;
      boxBounds = getBounds(block);
    }
  });
}
