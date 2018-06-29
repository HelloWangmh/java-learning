package wang.mh.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.ByteBuffer;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuffer>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg) throws Exception {

    }
}
